package com.shangho.blackcore.servlet.specialdemand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.specialdemand.DeleteSpecialDemandCategoryProcess;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.blackcore.api.specialdemand.request.DeleteSpecialDemandCategoryRequest;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/specialdemand/category/delete")
public class DeleteSpecialDemandCategoryServlet extends APIServlet {
	private static final long serialVersionUID = -6515743351715281657L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteSpecialDemandCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteSpecialDemandCategoryRequest entity = (DeleteSpecialDemandCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, DeleteSpecialDemandCategoryRequest.class);
		return new DeleteSpecialDemandCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
