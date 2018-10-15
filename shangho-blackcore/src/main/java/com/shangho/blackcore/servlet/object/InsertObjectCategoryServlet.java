package com.shangho.blackcore.servlet.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.object.InsertObjectCategoryProcess;
import com.shangho.blackcore.api.object.request.InsertObjectCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/object/category/insert")
public class InsertObjectCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 6937372474478968146L;
	private final static Logger logger = LoggerFactory.getLogger(InsertObjectCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertObjectCategoryRequest entity = (InsertObjectCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, InsertObjectCategoryRequest.class);
		return new InsertObjectCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
