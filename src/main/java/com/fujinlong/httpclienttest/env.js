
var CookieUtils = Java.type("com.fujinlong.httpclienttest.CookieUtils");
var JsBeauty=Java.type("com.fujinlong.httpclienttest.JsBeauty");
var BindFormToDocument=Java.type("com.fujinlong.httpclienttest.BindFormToDocument");
var SaveImg=Java.type("com.fujinlong.httpclienttest.SaveImg");

var Kkk=Java.type("com.fujinlong.httpclienttest.Kkk");
var ObjectResponse=Java.type("com.fujinlong.httpclienttest.ObjectResponse");



Object.prototype.__defineSetter__=function(name,fun){
	
	var descriptor = Object.getOwnPropertyDescriptor(this, name)||{ configurable: true};
	descriptor.set=fun;
	Object.defineProperty(this,name,descriptor);
};

Object.prototype.__defineGetter__=function(name,fun){
	var descriptor = Object.getOwnPropertyDescriptor(this, name)||{ configurable: true};
	descriptor.get=fun;
	Object.defineProperty(this,name,descriptor);
};

Object.prototype.__lookupSetter__=function(name){
	var descriptor = Object.getOwnPropertyDescriptor(this, name)||{};
	return descriptor.set;
};

Object.prototype.__lookupGetter__=function(name){
	var descriptor = Object.getOwnPropertyDescriptor(this, name)||{};
	return descriptor.get;
};

Object.defineProperty(Object.prototype,'__defineSetter__',{enumerable: false});

Object.defineProperty(Object.prototype,'__defineGetter__',{enumerable: false});
Object.defineProperty(Object.prototype,'__lookupSetter__',{enumerable: false});

Object.defineProperty(Object.prototype,'__lookupGetter__',{enumerable: false});


String.prototype.match=function(regex){return regex.exec(this);};



// The window Object
var window = this;
var top= window;
var parent =window;
var events = [{}];
(function(){

	// Browser Navigator

	// Helper method for generating the right
	// DOM objects based upon the type
	
	var obj_nodes = new java.util.HashMap();

	window.makeNode=function(node){
		if ( node ) {
			if ( !obj_nodes.containsKey( node ) ){
				obj_nodes.put( node, node.getNodeType() == 1?
					new DOMElement( node ) :
					node.getNodeType() == 8 ?
					new DOMComment( node ) :
					new DOMNode( node ) );
			}
			return obj_nodes.get(node);
		} else
			return null;
	}
	
	
	
	window.screen={
			height:1080,
			width:1920
	};
	window.navigator = {
		get userAgent(){
			return "5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36";
		},
		mimeTypes:{type:'application/x-nacl',description:"Native Client Executable"},
		appVersion:"5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36",
		appName:"Netscape"
		
		
	};
	
	var curLocation ;
	
	

	
	window.__defineSetter__("location", function(url){
		  var xhr = new XMLHttpRequest(); 
		  xhr.open("GET", url,false);
		  curLocation=new java.net.URL(url);
		  xhr.onreadystatechange = function(){ 
			  curLocation = new java.net.URL(url); 
			  var dom=org.jsoup.Jsoup.parse(xhr.responseText,url);
			  window.document = new DOMDocument(xhr.responseText,dom);
			  com.fujinlong.httpclienttest.jsExec.exec(curLocation,dom,httpClientContext,jsEngine);
			  BindFormToDocument.bind(dom,jsEngine);
			  var event = document.createEvent(); 
			  event.initEvent("load");
			  window.dispatchEvent( event ); 
		  };

		  xhr.send();
		
	});
	
	window.__defineGetter__("location", function(url){
		return {
			get protocol(){
				return curLocation.getProtocol() + ":";
			},
			get href(){
				return curLocation.toString();
			},
			set href(url){
				var xhr = new XMLHttpRequest(); 
				  xhr.open("GET", url,false);
				  print("打开："+url);
				  curLocation=new java.net.URL(url);
				  xhr.onreadystatechange = function(){ 
					  curLocation = new java.net.URL(url); 
					  var dom=org.jsoup.Jsoup.parse(xhr.responseText,url);
					  window.document = new DOMDocument(xhr.responseText,dom);
					  com.fujinlong.httpclienttest.jsExec.exec(curLocation,dom,httpClientContext,jsEngine);
					  BindFormToDocument.bind(dom,jsEngine);
					  var event = document.createEvent(); 
					  event.initEvent("load");
					  window.dispatchEvent( event ); 
				  };
				  xhr.send();
			},
			get hostname(){
				return curLocation.getHost();
			},
			toString: function(){
				return this.href;
			}
		};
	});
	
	// Timers

	var timers = [];
	
	window.setTimeout = function(fn, time){
		// print("延时执行："+fn);
		var num;
		return num = setInterval(function(){
			if(typeof fn==='string'){
				// print("延时执行xxxxx"+fn);
				jsEngine.eval(fn);
			}else{
				// print("延时执行notString"+fn);
				fn();
			}
			clearInterval(num);
		}, time);
	};
	
	window.setInterval = function(fn, time){
		// print("延时执行setInterval："+fn);
		var num = timers.length;
		
		timers[num] = new java.lang.Thread(new java.lang.Runnable({
			run: function(){
				while (true){
					// print(time);
					Packages.java.lang.Thread.sleep(time);
					fn();
				}
			}
		}));
		
		timers[num].start();
	
		return num;
	};
	
	window.clearTimeout=function(timeout){
		// print("清除timeout"+timeout);
		if(timeout){
			try{
				timers[timeout].stop();
			}catch(e){
				e.printStackTrace();
			}
			
		}
	}
	
	window.clearInterval = function(num){
		if ( timers[num] ) {
			timers[num].stop();
			delete timers[num];
		}
	};
	
	// Window Events
	
	

	window.addEventListener = function(type, fn){
		// print("添加事件："+type+"__"+this+"__"+fn);
		if ( !this.uuid || this == window ) {
			this.uuid = events.length;
			events[this.uuid] = {};
		}
	   
		if ( !events[this.uuid][type] )
			events[this.uuid][type] = [];
		
		if ( events[this.uuid][type].indexOf( fn ) < 0 )
			events[this.uuid][type].push( fn );
	};
	
	window.removeEventListener = function(type, fn){
	   if ( !this.uuid || this == window ) {
	       this.uuid = events.length;
	       events[this.uuid] = {};
	   }
	   
	   if ( !events[this.uuid][type] )
			events[this.uuid][type] = [];
			
		events[this.uuid][type] =
			events[this.uuid][type].filter(function(f){
				return f != fn;
			});
	};
	
	window.dispatchEvent = function(event){
		
		// print("分发事件："+event.type+"this.uuid:"+this.uuid+"event:"+events[this.uuid]);
		if ( event.type ) {
			if ( this.uuid && events[this.uuid][event.type] ) {
				var self = this;
				events[this.uuid][event.type].forEach(function(fn){
					fn.call( self, event );
				});
			}
			
			if ( this["on" + event.type] )
				this["on" + event.type].call( self, event );
		}
	};
	
	// DOM Document
	
	window.DOMDocument = function(file,dom){
		this._file = file;
		this._dom = dom;
		
		if ( !obj_nodes.containsKey( this._dom ) )
			obj_nodes.put( this._dom, this );
	};
	
	DOMDocument.prototype = {
		get referrer(){
			return 'https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=mail.qq.com&oq=sublime%20%E8%B7%B3%E8%BD%AC%E6%8C%87%E5%AE%9A%E8%A1%8C&rsv_pq=c9122210000f41db&rsv_t=051dWG2wT3Q5vS8%2BbsEd2A7sBcRuOx7e80MfmAW56%2BLzYwikadEbPJtMfnE&rqlang=cn&rsv_enter=1&inputT=20824&rsv_sug3=16&rsv_sug1=17&rsv_sug7=100&bs=sublime%20%E8%B7%B3%E8%BD%AC%E6%8C%87%E5%AE%9A%E8%A1%8C';
		},	
		get nodeType(){
			return 9;
		},
		createTextNode: function(text){
			return makeNode( this._dom.createTextNode(
				text.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")) );
		},
		createElement: function(name){
			return makeNode( this._dom.createElement(name.toLowerCase()) );
		},
		getElementsByTagName: function(name){
			return new DOMNodeList( this._dom.getElementsByTag(
				name.toLowerCase()) );
		},
		getElementsByName: function(name){
			var elems = this._dom.getElementsByTagName("*"), ret = [];
			ret.item = function(i){ return this[i]; };
			ret.getLength = function(){ return this.length; };
			
			for ( var i = 0; i < elems.length; i++ ) {
				var elem = elems.item(i);
				if ( elem.getAttribute("name") == name )
					ret.push( elem );
			}
			
			return new DOMNodeList( ret );
		},
		getElementById: function(id){
			// print("选择id为"+id+"的元素");
			
			if(id==='auth_low_login_enable'){
				id='auth_low_login_box';
			}
			// print(this._dom.select("#"+id).first());
			return makeNode(this._dom.select("#"+id).first());
		},
		get body(){
			return this.getElementsByTagName("body")[0];
		},
		get documentElement(){
			return makeNode( this._dom );
		},
		
		addEventListener: window.addEventListener,
		removeEventListener: window.removeEventListener,
		dispatchEvent: window.dispatchEvent,
		attachEvent:window.addEventListener,
		importNode: function(node, deep){
			return makeNode( this._dom.importNode(node._dom, deep) );
		},
		toString: function(){
			return "Document" + (typeof this._file == "string" ?
				": " + this._file : "");
		},
		get innerHTML(){
			return this.documentElement.outerHTML;
		},
		
		get defaultView(){
			return {
				getComputedStyle: function(elem){
					return {
						getPropertyValue: function(prop){
							prop = prop.replace(/\-(\w)/g,function(m,c){
								return c.toUpperCase();
							});
							var val = elem.style[prop];
							
							if ( prop == "opacity" && val == "" )
								val = "1";
								
							return val;
						}
					};
				}
			};
		},
		
		createEvent: function(){
			return {
				type: "",
				initEvent: function(type){
					this.type = type;
				},
				preventDefault:function(){}
			};
		},
		write:function(o){
			this._dom.append(o);
		},
		get cookie(){
			return CookieUtils.toStr(httpClientContext);
		},
		get location(){
			return window.location;
		},
		get ownerDocument(){
			// print(this+'ownerDocument:'+this._dom.ownerDocument());
			return makeNode(this._dom.ownerDocument());
		},
	};
	
	function getDocument(node){
		return obj_nodes.get(node);
	}
	
	// DOM NodeList
	
	window.DOMNodeList = function(list){
		this._dom = list;
		this.length = list.size();
		
		for ( var i = 0; i < this.length; i++ ) {
			var node = list.get(i);
			this[i] = makeNode( node );
		}
	};
	
	DOMNodeList.prototype = {
		toString: function(){
			return "[ " +
				Array.prototype.join.call( this, ", " ) + " ]";
		},
		get outerHTML(){
			return Array.prototype.map.call(
				this, function(node){return node.outerHTML;}).join('');
		}
	};
	
	// DOM Node
	
	window.DOMNode = function(node){
		this._dom = node;
	};
	
	DOMNode.prototype = {
		get nodeType(){
			return this._dom.getNodeType();
		},
		
		get childNodes(){
			return new DOMNodeList( this._dom.childNodes() );
		},
		cloneNode: function(deep){
			return makeNode( this._dom.cloneNode(deep) );
		},
		get ownerDocument(){
			//print(this+'ownerDocument:'+this._dom.ownerDocument());
			return makeNode( this._dom.ownerDocument() );
		},
		get documentElement(){
			return makeNode( this._dom.documentElement );
		},
		get parentNode() {
			return makeNode( this._dom.parentNode() );
		},
		get nextSibling() {
			return makeNode( this._dom.nextSibling() );
		},
		get previousSibling() {
			return makeNode( this._dom.previousSibling() );
		},
		toString: function(){
			return '未实现';
		},
	
	};

	window.DOMComment = function(node){
		this._dom = node;
	};

	DOMComment.prototype = extend(new DOMNode(), {
		get nodeType(){
			return 8;
		},
		get outerHTML(){
			return "<!--" + this.nodeValue + "-->";
		}
	});

	// DOM Element

	window.DOMElement = function(elem){
		if(!elem) return;
		this._dom = elem;
		this.init();
		
	};
	
	
	
	DOMElement.prototype = extend( new DOMNode(), {
		init:function(){
			this.style = {
					get opacity(){ return this._opacity; },
					set opacity(val){ this._opacity = val + ""; }
				};
				
				// Load CSS info
				var styles = (this.getAttribute("style") || "").split(/\s*;\s*/);
				
				for ( var i = 0; i < styles.length; i++ ) {
					var style = styles[i].split(/\s*:\s*/);
					if ( style.length == 2 )
						this.style[ style[0] ] = style[1];
				}
				
				if ( this.tagName == "FORM" ) {
					this.__defineGetter__("elements", function(){
						return this.getElementsByTagName("*");
					});
					
					this.__defineGetter__("length", function(){
						var elems = this.elements;
						for ( var i = 0; i < elems.length; i++ ) {
							this[i] = elems[i];
						}
						
						return elems.length;
					});
				}

				if ( this.tagName == "SELECT" ) {
					this.__defineGetter__("options", function(){
						return this.getElementsByTagName("option");
					});
				}

				this.defaultValue = this.value+"";
			
		},
		get tagName(){
			return this._dom.tagName().toLocaleUpperCase();
		},
		toString: function(){
			return "<" + this.tagName + (this.id ? "#" + this.id : "" ) + ">";
		},
		String: function(){
			return "<" + this.tagName + (this.id ? "#" + this.id : "" ) + ">";
		},
		get outerHTML(){
			var ret = "<" + this.tagName, attr = this.attributes;
			
			for ( var i in attr ){
				ret += " " + i + "='" + attr[i] + "'";
			}
				
			if ( this.childNodes.length || this.tagName == "SCRIPT" )
				ret += ">" + this.childNodes.outerHTML + 
					"</" + this.tagName + ">";
			else
				ret += "/>";
			
			return ret;
		},
		
		get attributes(){
			var attr = {}, attrs = this._dom.attributes().asList();

			for ( var i = 0; i < attrs.size(); i++ ){
				attr[ attrs.get(i).getKey() ] = attrs.get(i).getValue();
			}
				
			return attr;
		},
		
		get innerHTML(){
			return this._dom.html();	
		},
		set innerHTML(html){
			/*
			 * html = html.replace(/<\/?([A-Z]+)/g, function(m){ return
			 * m.toLowerCase(); }).replace(/&nbsp;/g, " ");
			 * 
			 * var nodes = this.ownerDocument.importNode( new DOMDocument( new
			 * java.io.ByteArrayInputStream( (new java.lang.String("<wrap>" +
			 * html + "</wrap>")) .getBytes("UTF8"))).documentElement,
			 * true).childNodes;
			 * 
			 * while (this.firstChild) this.removeChild( this.firstChild );
			 * 
			 * for ( var i = 0; i < nodes.length; i++ ) this.appendChild(
			 * nodes[i] );
			 */
			this._dom.html(html);
			
		},
		
		get textContent(){
			return nav(this.childNodes);
			
			function nav(nodes){
				var str = "";
				for ( var i = 0; i < nodes.length; i++ )
					if ( nodes[i].nodeType == 3 )
						str += nodes[i].nodeValue;
					else if ( nodes[i].nodeType == 1 )
						str += nav(nodes[i].childNodes);
				return str;
			}
		},
		set textContent(text){
			while (this.firstChild)
				this.removeChild( this.firstChild );
			this.appendChild( this.ownerDocument.createTextNode(text));
		},
		
		style: {},
		clientHeight: 0,
		clientWidth: 0,
		offsetHeight: 0,
		offsetWidth: 0,
		
		get disabled() {
			var val = this.getAttribute("disabled");
			return val != "false" && !!val;
		},
		set disabled(val) { return this.setAttribute("disabled",val); },
		
		get checked() {
			var val = this.getAttribute("checked");
			return val != "false" && !!val;
		},
		set checked(val) { return this.setAttribute("checked",val); },
		
		get selected() {
			if ( !this._selectDone ) {
				this._selectDone = true;
				
				if ( this.tagName == "OPTION" && !this.parentNode.getAttribute("multiple") ) {
					var opt = this.parentNode.getElementsByTagName("option");
					
					if ( this == opt[0] ) {
						var select = true;
						
						for ( var i = 1; i < opt.length; i++ )
							if ( opt[i].selected ) {
								select = false;
								break;
							}
							
						if ( select )
							this.selected = true;
					}
				}
			}
			
			var val = this.getAttribute("selected");
			return val != "false" && !!val;
		},
		set selected(val) { return this.setAttribute("selected",val); },

		get className() { return this.getAttribute("class") || ""; },
		set className(val) {
			return this.setAttribute("class",
				val.replace(/(^\s*|\s*$)/g,""));
		},
		
		get type() { return this.getAttribute("type") || ""; },
		set type(val) { return this.setAttribute("type",val); },

		get defaultValue() { return this.getAttribute("defaultValue") || ""; },
		set defaultValue(val) { return this.setAttribute("defaultValue",val); },

		get value() { 
			if(this.getAttribute("value")){
				return this.getAttribute("value");
			}else{
				return "";
			}
		},
		set value(val) { return this.setAttribute("value",val); },
		
		get src() { return this.getAttribute("src") || ""; },
		
		set src(val) { 
			// print("设置Src:"+this.tagName+":"+val);
			var xhr = new XMLHttpRequest();
			xhr.open("GET", val);
			var self=this;
			xhr.onreadystatechange = function(){
				self.onload && self.onload();
				// print("val.hashCode()"+val.hashCode());
				//print("self.tagName"+self.tagName)
				if(self.tagName=='IMG'){
					SaveImg.save(xhr.responseText,val.hashCode()+"",val);
				}
				
			};
			xhr.send();
			return this.setAttribute("src",val);
		},
		set href(val){
			return this.setAttribute("href",val);
		},
		get href(){
			return this.getAttribute("href") || "";
		},
		get id() { return this.getAttribute("id") || ""; },
		set id(val) { return this.setAttribute("id",val); },
		
		getAttribute: function(name){
			return this._dom.attr(name) ?
				new String( this._dom.attr(name) ) :
				null;
		},
		setAttribute: function(name,value){
			// print(name+"______"+value);
			if(value){value=value+""};
			this._dom.attr(name,value);
		},
		removeAttribute: function(name){
			this._dom.removeAttribute(name);
		},
		
		get childNodes(){
			return new DOMNodeList( this._dom.childNodes() );
		},
		get firstChild(){
			return makeNode( this._dom.getFirstChild() );
		},
		get lastChild(){
			return makeNode( this._dom.getLastChild() );
		},
		appendChild: function(node){
			this._dom.appendChild( node._dom );
		},
		insertBefore: function(node,before){
			this._dom.insertBefore( node._dom, before ? before._dom : before );
			
			execScripts( node );
			
			function execScripts( node ) {
				if ( node.tagName == "SCRIPT" ) {
					if ( !node.getAttribute("src") ) {
						eval.call( window, node.textContent );
					}
				} else {
					var scripts = node.getElementsByTagName("script");
					for ( var i = 0; i < scripts.length; i++ ) {
						execScripts( node );
					}
				}
			}
		},
		removeChild: function(node){
			this._dom.removeChild( node._dom );
		},

		getElementsByTagName: DOMDocument.prototype.getElementsByTagName,
		
		addEventListener: window.addEventListener,
		removeEventListener: window.removeEventListener,
		dispatchEvent: window.dispatchEvent,
		
		click: function(){
			var event = document.createEvent();
			event.initEvent("click");
			this.dispatchEvent(event);
		},
		submit: function(){
			var event = document.createEvent();
			event.initEvent("submit");
			this.dispatchEvent(event);
		},
		focus: function(){
			var event = document.createEvent();
			event.initEvent("focus");
			this.dispatchEvent(event);
		},
		blur: function(){
			var event = document.createEvent();
			event.initEvent("blur");
			this.dispatchEvent(event);
		},
		keydown: function(){
			var event = document.createEvent();
			event.initEvent("keydown");
			this.dispatchEvent(event);
		},
		keyup: function(){
			var event = document.createEvent();
			event.initEvent("keyup");
			this.dispatchEvent(event);
		},
		get contentWindow(){
			return this.tagName == "IFRAME" ? {
				document: this.contentDocument
			} : null;
		},
		get contentDocument(){
			if ( this.tagName == "IFRAME" ) {
				if ( !this._doc )
					this._doc = new DOMDocument(
						new java.io.ByteArrayInputStream((new java.lang.String(
						"<html><head><title></title></head><body></body></html>"))
						.getBytes("UTF8")));
				return this._doc;
			} else
				return null;
		},
		get hostname(){
			return this._dom.getHostname();
		},
		verifyimgNotify:function(){}
	});
	
	window.Image=function(){
		this._dom=document._dom.createElement('img');
		this.init();
	};
	
	Image.prototype =  new DOMElement();
	
	// Helper method for extending one object with another
	
	function extend(a,b) {
		for ( var i in b ) {
			var g = b.__lookupGetter__(i), s = b.__lookupSetter__(i);
			
			if ( g || s ) {
				if ( g )
					a.__defineGetter__(i, g);
				if ( s )
					a.__defineSetter__(i, s);
			} else
				a[i] = b[i];
		}
		return a;
	}
	
	
	
	// XMLHttpRequest
	// Originally implemented by Yehuda Katz

	window.XMLHttpRequest = function(){
		this.headers = {};
		this.responseHeaders = {};
	};
	
	XMLHttpRequest.prototype = {
		open: function(method, url, async, user, password){ 
			this.readyState = 1;
			if (async){
				this.async = true;
			}
			this.method = method || "GET";
			this.url = url;
			this.onreadystatechange();
		},
		setRequestHeader: function(header, value){
			this.headers[header] = value;
		},
		getResponseHeader: function(header){ },
		send: function(data){
			var self = this;
			// print(com.fujinlong.httpclienttest.HttpUtils.get("http://www.baidu.com"));
			// print(httpClientContext);
			function makeRequest(){
				var url = new java.net.URL(curLocation, self.url);
				if ( url.getProtocol() == "file" ) {
					if ( self.method == "PUT" ) {
						var out = new java.io.FileWriter( 
								new java.io.File( new java.net.URI( url.toString() ) ) ),
							text = new java.lang.String( data || "" );
						
						out.write( text, 0, text.length() );
						out.flush();
						out.close();
					} else if ( self.method == "DELETE" ) {
						var file = new java.io.File( new java.net.URI( url.toString() ) );
						file["delete"]();
					} else {
						var connection = url.openConnection();
						connection.connect();
						handleResponse();
					}
				} else { 
					handleResponse();
				}
				
				function handleResponse(){
					var response
					try{
						print('请求链接：'+url.toString());
						response=com.fujinlong.httpclienttest.HttpUtils.get(url,httpClientContext);
						print("xxxxxxxxxxxxxxxxx");
						if(response.isString()){
							self.responseText=response.getStringResponseBody();
							print("请求内容："+response.getCharset()+":"+self.responseText);
						}else if(response.isBinary()){
							self.responseText=response.getBinaryResponseBody();
						}else{
							throw new Exception('未知结果类型');
						}
						
					

					}catch(e){
						// print("加载连接出错："+url.toString());
						// print("出错原因："+e);
						e.printStackTrace();
					}
					
					// application/x-javascript
					if(url.getPath().endsWith(".js")||(response!=null&&response.getMimeType()=='application/x-javascript')){
						try{
							jsEngine.eval(JsBeauty.beauty(self.responseText));
						}catch(e){		
							 print(JsBeauty.beauty(self.responseText));
							throw e;
						}
					}
				
					
				}
				
				self.onreadystatechange();
			}

			if (this.async){
			
				(new java.lang.Thread(new java.lang.Runnable({
					run: makeRequest
				}))).start();
			}	
			else{
				makeRequest();
			}
		},
		abort: function(){},
		onreadystatechange: function(){},
		getResponseHeader: function(header){
			if (this.readyState < 3)
				throw new Error("INVALID_STATE_ERR");
			else {
				var returnedHeaders = [];
				for (var rHeader in this.responseHeaders) {
					if (rHeader.match(new RegExp(header, "i")))
						returnedHeaders.push(this.responseHeaders[rHeader]);
				}
			
				if (returnedHeaders.length)
					return returnedHeaders.join(", ");
			}
			
			return null;
		},
		getAllResponseHeaders: function(header){
			if (this.readyState < 3)
				throw new Error("INVALID_STATE_ERR");
			else {
				var returnedHeaders = [];
				
				for (var header in this.responseHeaders)
					returnedHeaders.push( header + ": " + this.responseHeaders[header] );
				
				return returnedHeaders.join("\r\n");
			}
		},
		async: false,
		readyState: 0,
		responseText: "",
		status: 0
	};
})();