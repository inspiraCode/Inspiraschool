package com.inspiracode.inspiraschool.jsf.beans;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import org.apache.log4j.Logger;

import com.inspiracode.inspiraschool.dto.BaseDTO;

public abstract class BaseFacesReporteableBean<T extends BaseDTO> extends BaseFacesBean<T> {
    private static final long serialVersionUID = -1504000319744782357L;
    private static final Logger logger = Logger.getLogger(BaseFacesReporteableBean.class.getName());

    private JasperPrint jasperPrint;

    public BaseFacesReporteableBean(Class<T> type) {
	super(type);
    }

    public String pdfSQLReport(String reportName, Map<String, Object> params) {
	logger.debug("downloading report " + reportName);
	try {
	    initializeReport(reportName, params);
	    OutputPdfReport(reportName);
	} catch (JRException e) {
	    publishError(e.getMessage());
	    logger.error(e.getMessage(), e);
	}
	return "";
    }

    public String xlsSQLReport(String reportName, Map<String, Object> params) {
	try {
	    initializeReport(reportName, params);
	    OutputXlsReport(reportName);
	} catch (JRException e) {
	    publishError(e.getMessage());
	    logger.error(e.getMessage(), e);
	}
	return "";
    }

    public String pdfReport(List<? extends BaseDTO> reportList, String reportName) throws JRException, IOException {
	initializeReport(reportList, reportName);
	OutputPdfReport(reportName);
	return "";
    }

    private void OutputPdfReport(String reportName) {
	logger.debug("Generating output PDF report");
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".pdf");
	try {
	    ServletOutputStream servletOutputStream = response.getOutputStream();
	    JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
	} catch (Exception e) {
	    publishError("No fue posible formar la respuesta del reporte: " + e.getMessage());
	    logger.error(e.getMessage(), e);
	}

	FacesContext.getCurrentInstance().responseComplete();
    }

    private void OutputXlsReport(String reportName) {
	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	response.addHeader("Content-disposition", "attachment; filename=" + reportName + ".xlsx");
	ServletOutputStream servletOutputStream;
	try {
	    servletOutputStream = response.getOutputStream();
	} catch (IOException e) {
	    publishError("Error al calcular la salida del archivo. Consulte al administrador con la siguiente información de referencia: " + e.getMessage());
	    logger.error(e.getMessage(), e);
	    return;
	}

	JRXlsxExporter docxExporter = new JRXlsxExporter();
	docxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(servletOutputStream));

	SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
	configuration.setOnePagePerSheet(false);
	configuration.setDetectCellType(true);
	configuration.setCollapseRowSpan(false);
	docxExporter.setConfiguration(configuration);

	try {
	    docxExporter.exportReport();
	} catch (JRException e) {
	    publishError("Error al exportar reporte en formato de excel: " + e.getMessage());
	    logger.error(e.getMessage(), e);
	}

	FacesContext.getCurrentInstance().responseComplete();
    }

    public String xlsReport(List<? extends BaseDTO> reportList, String reportName) throws JRException, IOException {
	initializeReport(reportList, reportName);
	OutputXlsReport(reportName);
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
	logger.debug("compiling as report: " + reportPath);
	File jasperFile = new File(reportPath);
	logger.debug("file read, checking existance");
	if (!jasperFile.exists()) {
	    try {
		String xmlFileName = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jrxml");
		logger.debug("compiling report: " + xmlFileName);
		jasperFile = new File(xmlFileName);

		if (!jasperFile.exists())
		    throw new Exception("No se encontró el archivo del reporte: " + xmlFileName);

		JasperCompileManager.compileReportToFile(xmlFileName, reportPath);
	    } catch (Exception e) {
		publishError("Error al compilar reporte: " + reportName);
		logger.error(e.getMessage(), e);
		return false;
	    }
	}

	return true;
    }

    private void initializeReport(List<? extends BaseDTO> reportList, String reportName) throws JRException {
	JRBeanCollectionDataSource connectionDS = new JRBeanCollectionDataSource(reportList);

	if (!compileReport(reportName)) {
	    throw new JRException("No fue posible compilar el reporte");
	}

	String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jasper");

	File jasperFile = new File(reportPath);
	if (!jasperFile.exists()) {
	    throw new JRException("No se encontró el archivo del reporte: " + reportPath);
	}
	Map<String, Object> params = new HashMap<String, Object>();
	jasperPrint = JasperFillManager.fillReport(reportPath, params, connectionDS);
    }

    private void initializeReport(String reportName, Map<String, Object> params) throws JRException {
	logger.debug("Initializing report: " + reportName);
	if (!compileReport(reportName)) {
	    logger.error("No fue posible compilar el reporte");
	    throw new JRException("No fue posible compilar el reporte");
	}

	String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/" + reportName + ".jasper");

	File jasperFile = new File(reportPath);
	if (!jasperFile.exists()) {
	    logger.error("file not found: " + reportPath);
	    throw new JRException("No se encontró el archivo del reporte: " + reportPath);
	}
	jasperPrint = JasperFillManager.fillReport(reportPath, params, this.getDBConnection());
	if (jasperPrint == null) {
	    logger.error("could not fill report with selectedparameters: " + params);
	    Set<String> pKeySet = params.keySet();
	    for (String s : pKeySet) {
		logger.debug("param " + s + " : " + params.get(s));
	    }
	    throw new JRException("No se pudo llenar el reporte con los parámetros seleccionados");
	}
    }

}
