var mapFunction2 = function() {
     emit(this.job,null)
};

var reduceFunction2 = function(key, values) {
  
  reducedVal = { dummy: 0 };
  reducedVal.dummy=0
  return reducedVal;
   
   
};


db.people.mapReduce(
   mapFunction2,
   reduceFunction2,
   {
	 
     out: "map_reduce_3" 
     
   }

)

printjson(db.map_reduce_3.find().sort( { _id: -1 } ).toArray()
);
