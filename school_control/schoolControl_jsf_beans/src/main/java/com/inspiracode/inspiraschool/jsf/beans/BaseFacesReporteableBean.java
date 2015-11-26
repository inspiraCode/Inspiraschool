package com.inspiracode.inspiraschool.jsf.beans;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public abstract class BaseFacesReporteableBean<T extends BaseDTO> extends BaseFacesBean<T> {
    private static final long serialVersionUID = -1504000319744782357L;
    
    private JasperPrint jasperPrint;

    public BaseFacesReporteableBean(Class<T> type) {
	super(type);
    }
    
    public String pdfReport(List<? extends BaseDTO> reportList, String reportName) throws JRException, IOException {
	initializeReport(reportList, reportName);
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".pdf");
	ServletOutputStream servletOutputStream = response.getOutputStream();
	JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	FacesContext.getCurrentInstance().responseComplete();
	return "";
    }

    public String xlsReport(List<? extends BaseDTO> reportList, String reportName) throws JRException, IOException {
	initializeReport(reportList, reportName);
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".xlsx");
	ServletOutputStream servletOutputStream = response.getOutputStream();

	JRXlsxExporter docxExporter = new JRXlsxExporter();
	docxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));

	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
	configuration.setOnePagePerSheet(false);
	configuration.setDetectCellType(true);
	configuration.setCollapseRowSpan(false);
	docxExporter.setConfiguration(configuration);

	docxExporter.exportReport();

	FacesContext.getCurrentInstance().responseComplete();
	return "";
    }

    public JasperPrint getJasperPrint() {
	return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
	this.jasperPrint = jasperPrint;
    }
    
    private boolean compileReport(String reportName) {
	String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jasper");

	File jasperFile = new File(reportPath);
	if (!jasperFile.exists()) {
	    try {
		String xmlFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jrxml");
		jasperFile = new File(xmlFileName);

		if (!jasperFile.exists())
		    throw new Exception("report sourcecode file " + xmlFileName + "not found");

		JasperCompileManager.compileReportToFile(xmlFileName, reportPath);
	    } catch (Exception e) {
		publishError("Error al compilar reporte: " + reportName);
		publishError(e.getLocalizedMessage());
		return false;
	    }
	}

	return true;
    }
    
    private void initializeReport(List<? extends BaseDTO> reportList, String reportName) throws JRException {
	JRBeanCollectionDataSource connectionDS = new JRBeanCollectionDataSource(reportList);

	if (!compileReport(reportName))
	    throw new JRException("Could not compile report.");

	String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jasper");

	File jasperFile = new File(reportPath);
	if (!jasperFile.exists()) {
	    throw new JRException("Report not found " + reportPath);
	}
	Map<String, Object> params = new HashMap<String, Object>();
	jasperPrint = JasperFillManager.fillReport(reportPath, params, connectionDS);
    }
}
