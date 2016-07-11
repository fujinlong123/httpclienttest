
/**
 * 模拟浏览器环境
 */


var window=this;




navigator={
		userAgent:'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.155 Safari/537.36',
};

location={
		protocol:'https:',	
}

img=function(){
	attr:{},
	get src(){return attr.str;},
	set src(src){attr.str=src;},
};


DOMDocument=function(){
	
}

DOMDocument.prototype={
		createElement:function(tag){
			if(tag==='img'){
				return new img();
			}
			throw new Exception();
		},
		write:print,
}


document=new DOMDocument();



