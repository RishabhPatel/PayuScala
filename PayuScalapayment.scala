object Payu{

//payu Integration
  def getPaymentHash(Key : String , Amount : String , ProdutInfo : String , FirstName : String , Email : String) : String = {    
	import java.util._		 	  
	val salt="3sf0jURk" //salt value store	  
	var hashString = ""  //define hashstring
	var hash="" //define hash  
	//get txnid  
	val  TxnId =   texnId	  
	//create hashMap
	val params  =  new HashMap[String, String]	
	//required field
	params put("key", Key) //store key value
	params put("txnid", TxnId) //store txnid
	params put("amount", Amount) //store amount
	params put("productinfo", ProdutInfo) //store
	params put("firstname", FirstName) //store firstname
	params put("email", Email)	// store email
	///define hashsquen key  
	val hashSequence = "key|txnid|amount|productinfo|firstname|email"	  
	var   hashVarSeq : Array[String]  = Array()	
	hashVarSeq = hashSequence.split("\\|")	
	for(part :String <- hashVarSeq){	
	 hashString= (hashString.concat(params.get(part)))	
	 hashString=hashString.concat("|")	
	}
	//add stirng
	hashString=hashString.concat(salt)
	//genrate hash
	hash=hashCal("SHA-512",hashString)
	
	return hash	
  }
  
  
  //get action url
  def ActionUrl : String = {    
    val base_url="https://test.payu.in"
    val actionurl = base_url.concat("/_payment")
    return actionurl
  }
  
  //get marchant key
  def getMarchantKey : String = {    
    val merchant_key="C0Dr8m"      
    return merchant_key  
  }
  
  //get texnId
  def texnId : String = {
		import java.util._	
		val rand = new Random()
		val rndm = Integer.toString(rand.nextInt())+(System.currentTimeMillis() / 1000L)
		val txnid=hashCal("SHA-256",rndm).substring(0,20)
		return txnid    
  }  
  
  //hash call
  def hashCal(ptype : String, str : String ) : String =  {
		import java.util._
		import java.security._  
        var hashseq : Array[Byte]  =  Array()
		hashseq = str.getBytes()
		val hexString = new StringBuffer()		
		try{
		val algorithm = MessageDigest.getInstance(ptype)
		algorithm.reset()
		algorithm.update(hashseq)
		var messageDigest : Array[Byte] = Array()
		messageDigest = algorithm.digest()	
		for( p : Int  <- 0 to messageDigest.length){	
		    var payu = messageDigest(p)
			val hex=Integer.toHexString(0xFF & payu)
			if(hex.length()==1) hexString.append("0")
			hexString.append(hex)
		}
			
	}catch{ 
		  case ex: NoSuchAlgorithmException => println("Exception") 
		  }
		
		
		return hexString.toString()
	}
}
	