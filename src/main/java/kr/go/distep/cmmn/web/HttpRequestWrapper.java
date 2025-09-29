package kr.go.distep.cmmn.web;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/*
 * 
 * HttpServletRequest 클래스를 wrap하기 위한 클래스
 * 
 * */
public class HttpRequestWrapper extends HttpServletRequestWrapper{
	
	private Map<String, String[]> allParameters = null;
	
	public HttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public String getParam(final String name){
		return getParameter(name);
	}
	
	public Map<String, String[]> getParamMap() {
		return getParameterMap();
	}
	
	public Enumeration<String> getParamNames() {
		return getParameterNames();
	}
	
	public String[] getParamValues(final String name) {
		return getParameterValues(name);
	}
	
	public String getHdr(String name) {
		return getHeader(name);
	}
	
	@Override
    public String getParameter(final String name) {
        String[] strings = getParameterMap().get(name);
        if (strings != null) {
            return strings[0];
        }
        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if (allParameters == null) {
            allParameters = new TreeMap<String, String[]>();
            allParameters.putAll(super.getParameterMap());
        }
        
        
        Map<String, String[]> map = new HashMap<String, String[]>();
    	Iterator iterator = allParameters.entrySet().iterator(); 
		while(iterator.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iterator.next(); 
		    String key = (String) entry.getKey(); 
		    String[] value = (String[]) entry.getValue(); 
		    map.put(key, value); 
		}
    	
        return map;
        // Return an unmodifiable collection because we need to uphold the interface contract.
        //return Collections.unmodifiableMap(allParameters);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(final String name) {
        return getParameterMap().get(name);
    }
    
    @Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        return (header != null) ? header : super.getParameter(name); // Note: you can't use getParameterValues() here.
    }
}
