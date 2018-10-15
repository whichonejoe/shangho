package com.shangho.blackcore.servlet.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.object.UpdateObjectCategoryProcess;
import com.shangho.blackcore.api.object.request.UpdateObjectCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/object/category/update")
public class UpdateObjectCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 1236082526340191187L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateObjectCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateObjectCategoryRequest entity = (UpdateObjectCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, UpdateObjectCategoryRequest.class);
		return new UpdateObjectCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
