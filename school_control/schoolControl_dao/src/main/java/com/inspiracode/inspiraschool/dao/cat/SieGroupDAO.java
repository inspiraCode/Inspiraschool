package com.inspiracode.inspiraschool.dao.cat;

import com.inspiracode.inspiraschool.dao.BaseDAO;
import com.inspiracode.inspiraschool.dto.cat.SieGroup;

public interface SieGroupDAO extends BaseDAO<SieGroup> {
    public final static String QUERY_FETCH_SCORES = "from SieGroup sg left join fetch sg.students st left join fetch st.scores sc "
	    + "left join fetch sc.groupAssignment ga WHERE sg.id=? AND ga.group.period.id = sg.period.id";

    SieGroup getSieWithScores(int sieGroupId);

}
