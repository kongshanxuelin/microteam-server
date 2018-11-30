package com.sumslack.web.working.handler;

import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.dao.M_work_tmpl_fieldsDAO;
import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;
import com.sumslack.web.working.service.TmplService;

public class ExpressionFieldValueHandler extends AbsFieldValueHandler{
	@Override
	public String handlerValue(M_work_tmpl_fields_valueDAO fieldValue) {
		M_work_tmpl_fieldsDAO _field = TmplService.getInstance().getField(fieldValue.getField_id());
		if(_field!=null){
			String _fieldDefaultValue = StrUtil.formatNullStr(_field.getUi_defaultvalue());
			if(_fieldDefaultValue.startsWith("expr:")){
				_fieldDefaultValue = _fieldDefaultValue.substring(4);
			}
			return fieldValue.getField_value();
		}
		return null;
	}
}
