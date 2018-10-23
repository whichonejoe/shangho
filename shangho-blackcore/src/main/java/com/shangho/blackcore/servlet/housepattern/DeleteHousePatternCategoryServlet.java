package com.shangho.blackcore.servlet.housepattern;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.housepattern.DeleteHousePatternCategoryProcess;
import com.shangho.blackcore.api.housepattern.request.DeleteHousePatternCategoryRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/housepattern/category/delete")
public class DeleteHousePatternCategoryServlet extends APIServlet {
	private static final long serialVersionUID = 9122851338402104651L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteHousePatternCategoryServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteHousePatternCategoryRequest entity = (DeleteHousePatternCategoryRequest) APIParser.getInstance()
				.parse(apiRequest, DeleteHousePatternCategoryRequest.class);
		return new DeleteHousePatternCategoryProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
