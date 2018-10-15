package com.shangho.blackcore.servlet.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.object.DeleteObjectCategoryProcess;
import com.shangho.blackcore.api.object.request.DeleteObjectCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/object/category/delete")
public class DeleteObjectCategoryServlet extends APIServlet {
	private static final long serialVersionUID = -515453733799734202L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteObjectCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteObjectCategoryRequest entity = (DeleteObjectCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, DeleteObjectCategoryRequest.class);
		return new DeleteObjectCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
