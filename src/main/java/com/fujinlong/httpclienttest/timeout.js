

(function(context) {
	  'use strict';
	  var Timer = Java.type('java.util.Timer');
	  var Phaser = Java.type('java.util.concurrent.Phaser');

	  var timer = new Timer('jsEventLoop', false);
	  var phaser = new Phaser();
	 
	  var onTaskFinished = function() {
	    phaser.arriveAndDeregister();
	  };
	 
	  context.setTimeout = function(fn, millis /* [, args...] */) {
	    var args = [].slice.call(arguments, 2, arguments.length);
	 
	    var phase = phaser.register();
	    var canceled = false;
	    var count=0;
	    timer.schedule(function() {
	      if (canceled) {
	        return;
	      }
	      try {
	    	  //print("执行setTimeout"+fn);
	        fn.apply(context, args);
	      } catch (e) {
	    	 // print("执行setTimeout出错："+fn);
	        print(e);
	      } finally {
	        onTaskFinished();
	      }
	    }, millis);
	 
	    var canfun=function() {
		      onTaskFinished();
		      canceled = true;
		    };
		timers.push(canfun);    
	    return canfun;
	  };
	 
	  context.clearTimeout = function(cancel) {
		  LogConfig.timeoutPrint('clearTimeout：'+cancel);
		  if(cancel){
			  cancel();
		  }
	  };
	 
	  context.setInterval = function(fn, delay /* [, args...] */) {
	    var args = [].slice.call(arguments, 2, arguments.length);
	 
	    var cancel = null;
	    var count=0; 
	    var loop = function() {
	    	LogConfig.timeoutPrint("setInterval:time:"+delay+":"+fn);
	    	if(count>5){
	    		print("定时任务执行超过5次强制停止"+delay+":"+fn);
	    		return;
	    	}
	    	count++;
	      cancel = context.setTimeout(loop, delay);
	      fn.apply(context, args);
	    };
	 
	    cancel = context.setTimeout(loop, delay);
	    return function() {
	    	var	xx=""+fn;
	      cancel();
	    };
	  };
	 
	  context.clearInterval = function(cancel) {
		  LogConfig.timeoutPrint('clearInterval：'+cancel);
		  if(cancel){
			  cancel();
		  }
	  };
	  
		var timers = [];
		context.clearAllTimers=function(){
				var canfun;
				while((canfun=timers.pop())){
					canfun();
				}
		};
	  
	 
	})(this);
