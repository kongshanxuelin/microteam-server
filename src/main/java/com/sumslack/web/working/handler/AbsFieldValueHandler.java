package com.sumslack.web.working.handler;

import com.sumslack.web.working.dao.M_work_tmpl_fields_valueDAO;

public abstract class AbsFieldValueHandler {
	protected AbsFieldValueHandler successor;
    public AbsFieldValueHandler getSuccessor() {
		return successor;
	}
	public void setSuccessor(AbsFieldValueHandler successor) {
		this.successor = successor;
	}
	public abstract String handlerValue(M_work_tmpl_fields_valueDAO fieldValue);
}
