package com.shangho.blackcore.servlet.object;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangho.api.object.ListDesignatePathProcess;
import com.shangho.blackcore.api.designatepath.request.ListDesignatePathRequest;
import com.shangho.blackcore.api.parser.APIParser;
import com.shangho.blackcore.api.parser.APIServlet;
import com.shangho.utils.exception.SHException;
import com.shangho.utils.log.LogAction;

@WebServlet("/object/designatepath/list")
public class ListDesignatePathServlet extends APIServlet {
	private static final long serialVersionUID = 7727639500306791834L;
	private final static Logger logger = LoggerFactory.getLogger(ListDesignatePathServlet.class);

	@Override
	protected Object execute(String apiRequest, HttpServletRequest req) throws SHException, Exception {
		@SuppressWarnings("unchecked")
		final ListDesignatePathRequest entity = (ListDesignatePathRequest) APIParser.getInstance().parse(apiRequest,
				ListDesignatePathRequest.class);
		return new ListDesignatePathProcess(entity).execute();
	}

	@Override
	protected void initial() throws SHException, Exception {
		// TODO Auto-generated method stub
		LogAction.getInstance().initial(logger, this.getClass().getSimpleName());
	}

}
