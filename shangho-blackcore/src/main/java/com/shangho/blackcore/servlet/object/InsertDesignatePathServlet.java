package com.shangho.blackcore.servlet.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.object.InsertDesignatePathProcess;
import com.shangho.blackcore.api.designatepath.request.InsertDesignatePathRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/object/designatepath/insert")
public class InsertDesignatePathServlet extends APIServlet {
	private static final long serialVersionUID = 6742490894563407362L;
	private final static Logger logger = LoggerFactory.getLogger(InsertDesignatePathServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final InsertDesignatePathRequest entity = (InsertDesignatePathRequest) APIParser.getInstance().parse(apiRequest,
				InsertDesignatePathRequest.class);
		return new InsertDesignatePathProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
