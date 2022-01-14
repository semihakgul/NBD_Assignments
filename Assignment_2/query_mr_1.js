var mapFunction1 = function() {
  
    var key =this.sex;
    var value = { weight:  parseFloat(this.weight), height: parseFloat(this.height) ,count:1};
	
	emit(key, value);
};

var reduceFunction1 = function(key, value) {
   
   reducedVal = { sweight: 0.00, sheight: 0.00,scount: 0.00 , avgweight:0.00};
   
   result = { avgweight:0, avgheight:0};
 
 for (var idx = 0; idx < value.length; idx++) {
       reducedVal.sweight +=value[idx].weight;
       reducedVal.sheight +=value[idx].height;
       reducedVal.scount+=value[idx].count;
   }
    
	result.avgweight = reducedVal.sweight/ reducedVal.scount;
	
	result.avgheight = reducedVal.sheight/ reducedVal.scount;
	
   return result;
};


db.people.mapReduce(
   mapFunction1,
   reduceFunction1,
  
  
   {
     out: "map_reduce_1" ,
     
   }

)

printjson(db.map_reduce_1.find().sort( { _id: -1 } ).toArray()
);
