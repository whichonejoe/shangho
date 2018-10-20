package com.shangho.blackcore.servlet.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.object.DeleteDesignatePathProcess;
import com.shangho.blackcore.api.designatepath.request.DeleteDesignatePathRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/object/designatepath/delete")
public class DeleteDesignatePathServlet extends APIServlet {
	private static final long serialVersionUID = -2372686684628075823L;
	private final static Logger logger = LoggerFactory.getLogger(DeleteDesignatePathServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final DeleteDesignatePathRequest entity = (DeleteDesignatePathRequest) APIParser.getInstance().parse(apiRequest,
				DeleteDesignatePathRequest.class);
		return new DeleteDesignatePathProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
