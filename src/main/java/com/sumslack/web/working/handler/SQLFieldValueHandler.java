package com.sumslack.web.working.handler;

import java.util.Map;

import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.dao.M_work_tmpl_fieldsDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;
import com.sumslack.web.working.service.TmplService;

public class SQLFieldValueHandler extends AbsFieldValueHandler{

	@Override
	public String handlerValue(M_work_tmpl_fields_valueDAO fieldValue) {
		M_work_tmpl_fieldsDAO _field = TmplService.getInstance().getField(fieldValue.getField_id());
		if(_field!=null){
			String _fieldDefaultValue = StrUtil.formatNullStr(_field.getUi_defaultvalue());
			if(_fieldDefaultValue.startsWith("sql:")){
				_fieldDefaultValue = _fieldDefaultValue.substring(4);
				_fieldDefaultValue = StrUtil.replace(_fieldDefaultValue, ":rec_id:", "'"+fieldValue.getTmpl_id_id()+"'");
				Map oneMap = null;
				try {
					oneMap = TagJDBCInstance.getInstance().queryOne(_fieldDefaultValue,null);
					if(oneMap!=null && oneMap.values().size()==1){
						return StrUtil.formatNullStr(oneMap.values().iterator().next());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			return fieldValue.getField_value();
		}
		return null;
	}

}
