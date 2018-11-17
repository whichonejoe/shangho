Handlebars.registerHelper('ifCond', function(v1, v2, options) {
    if (v1 == v2) {
        return options.fn(this);
    }
    return options.inverse(this);
});

Handlebars.registerHelper('ifNull', function(v1,options) {
    if (v1 != null && v1 !='') {
        return v1 + options.fn(this);
    }else{
    	return '';
    }
});
