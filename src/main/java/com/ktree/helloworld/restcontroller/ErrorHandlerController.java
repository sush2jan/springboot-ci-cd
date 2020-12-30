package com.ktree.helloworld.restcontroller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ErrorHandlerController implements ErrorController{
	private static final String PATH = "/demo";
	 @RequestMapping(value=PATH)
	 public String error() {
	  return "Welcome to release demo :)";
	 }
	 @Override
	 public String getErrorPath() {
	  return PATH;
	 }
}
