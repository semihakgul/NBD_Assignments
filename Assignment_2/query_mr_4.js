var mapFunction1 = function() {
  
    var key =this.nationality;
    var value = { bmi:  parseFloat(this.weight)/ ((parseFloat(this.height)/100)*(parseFloat(this.height)/100)) ,count:1};
	
	emit(key, value);
};

var reduceFunction1 = function(key, value) {
   
   reducedVal = { maxBmi:0.00,minBmi:10000, sBmi:0.00, scount:0.00 };
   
   result = {  avgBmi:0.00, maxBmi:0.00, minBmi:0};
   
 
 for (var idx = 0; idx < value.length; idx++) {
       reducedVal.maxBmi = Math.max( reducedVal.maxBmi , value[idx].bmi);
	   reducedVal.minBmi = Math.min( reducedVal.minBmi , value[idx].bmi);
	   reducedVal.sBmi += value[idx].bmi;
       reducedVal.scount+=value[idx].count;
   }
    
	result.maxBmi=reducedVal.maxBmi;
	result.minBmi=reducedVal.minBmi;
	result.avgBmi=reducedVal.sBmi/reducedVal.scount;
	
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
