package com.shangho.blackcore.servlet.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.object.ListObjectCategoryProcess;
import com.shangho.blackcore.api.object.request.ListObjectCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/object/category/list")
public class ListObjectCategoryServlet extends APIServlet {
	private static final long serialVersionUID = -314332397523711130L;
	private final static Logger logger = LoggerFactory.getLogger(ListObjectCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListObjectCategoryRequest entity = (ListObjectCategoryRequest) APIParser.getInstance().parse(apiRequest,
				ListObjectCategoryRequest.class);
		return new ListObjectCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
