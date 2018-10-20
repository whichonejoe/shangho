package com.shangho.blackcore.servlet.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.object.UpdateDesignatePathProcess;
import com.shangho.blackcore.api.designatepath.request.UpdateDesignatePathRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/object/designatepath/update")
public class UpdateDesignatePathServlet extends APIServlet {
	private static final long serialVersionUID = -1408304148629000219L;
	private final static Logger logger = LoggerFactory.getLogger(UpdateDesignatePathServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final UpdateDesignatePathRequest entity = (UpdateDesignatePathRequest) APIParser.getInstance().parse(apiRequest,
				UpdateDesignatePathRequest.class);
		return new UpdateDesignatePathProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
