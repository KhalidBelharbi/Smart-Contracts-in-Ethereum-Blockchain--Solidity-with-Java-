pragma solidity ^0.4.0;



contract Copyright {
    
    modifier onlyOwner { require(msg.sender == owner); _;}
    uint private id = 1 ; // id of each documents
    address public owner;
    enum DocState {None,Validated,Deleted} // state of the documents in the blockchain ; 
    mapping (address => mapping (bytes32 => DocumentData )) _data; // every user has a mapping list of his documents
    uint[] listID ;  // of docs  
    address[] users; // we hv to use this parameters to can get all the data in the mapping structure, else u cant do that;
    bytes32[] allHashs;
    struct DocumentData {
        DocState etat_;  // the state of the document;
        uint timestamp_creation; // the timestamp of the creation 
        address owner; // the adress of its owner ; 
        uint id_ ; // the id of this document ; 
        uint timestamp_deletion; // the timestamp when the owner deleted it ;
  }
        //  the constructor method
  constructor() public {
        owner = msg.sender; // initialisation of the contract; 
    }
    
     // fallback function 
    function() external payable  {}
    
    // ajouter un fichier _ le hash de fichier
    function AddFile(bytes32 hash_) external  payable {
        require(msg.value >= 5000000000000000000); // 0.5 ether = 5 * 10^17
        require(hash_ > 0); //not empty 
        require(caniaddit(hash_)); // le document ne doit pas etre existe deja - for all users 
        if( _data[msg.sender][hash_].etat_ == DocState.Deleted)
                _data[msg.sender][hash_].etat_ = DocState.Validated; 
        else{
            DocumentData memory newDataFile; // saving the hash in the memory not in the stack of the process ;
            newDataFile.etat_ = DocState.Validated ;  // Deleted  DocState.Validated 
            newDataFile.timestamp_creation = now;
            newDataFile.owner = msg.sender ;
            newDataFile.id_ = id; 
            newDataFile.timestamp_deletion = 0 ;
            id += 1 ;
            _data[msg.sender][hash_] = newDataFile ;
            listID.push(id);
            addUser(msg.sender);
            allHashs.push(hash_);
        }
    }
 
     function caniaddit(bytes32 hash_) private view returns(bool){
      if(!HashIsExist(hash_) || (_data[msg.sender][hash_].owner == msg.sender && _data[msg.sender][hash_].etat_ == DocState.Deleted )  )
            return true;
      else  return false ;    
    }
    
    function UserIsExist(address adr) private view returns(bool) {
          for (uint j = 0; j<users.length; j++)
            if(users[j]==adr) return true;
        return false; 
    }
   
       function HashIsExist(bytes32 h) public view returns(bool) {
        for (uint j = 0; j<allHashs.length; j++)
            if(allHashs[j] == h) return true;
        return false;
    }
    
    function HashIsExist(address user,bytes32 h) public view returns(bool) {
        return (_data[user][h].owner == user);
    } 
    
   function getAllUsers() public view returns(address[]){
        return users ;
    }
 
       // chaque utilisateur peut supprimé son document 
    function deleteDoc(bytes32 hash_) public {
        require(hash_ > 0); //le hash ne doit pas etre vide 
        require(HashIsExist(msg.sender ,hash_) );
        _data[msg.sender][hash_].etat_ = DocState.Deleted ; 
        _data[msg.sender][hash_].timestamp_deletion = now ; 
   }
    
   // return validate or not, deleted or not , time of action , adress of the owner 
    function getStateOfDoc(bytes32 hash_) public view returns(bool,bool,uint256,address){ 
        for(uint i=0; i <users.length;i++){
             if(_data[users[i]][hash_].etat_ == DocState.Validated)
                                          return (true,
                                          false,
                                          _data[users[i]][hash_].timestamp_creation,
                                          users[i]);
             if(_data[users[i]][hash_].etat_ == DocState.Deleted)
                                          return (false,true,
                                          _data[users[i]][hash_].timestamp_creation
                                          ,users[i]);
        }
        return (false,false,0,0);
    }
 
 
    function addUser(address adr) private {
        for (uint j = 0; j<users.length; j++)
            if(users[j]==adr) return;
        users.push(adr);
    }

    function getStructure(DocState a ,uint b ,address c ,uint d ,uint e ) private pure returns(DocumentData){
            DocumentData memory newDataFile; // saving the hash in the memory not in the stack of the process ;
            newDataFile.etat_ = a ; 
            newDataFile.timestamp_creation = b;
            newDataFile.owner = c;
            newDataFile.id_ = d; 
            newDataFile.timestamp_deletion = e ;
         return newDataFile ;
     }
    
 
      function isValidated(bytes32 hash_,address adr) public view returns(bool){
         return (_data[adr][hash_].etat_ == DocState.Validated);
    }
 
    
    function isLinkedTo(address user,bytes32 doc) public view returns(bool){
        return (_data[user][doc].owner == user) ;
    }
    
    
    

    
    function changeTheOwnerContract(address adr) public payable onlyOwner returns(bool)  {
        owner = adr ; 
        return true ;
    }
    
    
    
    function getBalance() public view onlyOwner returns(uint){
        return address(this).balance ; 
    }
    
    
    function withdrawMoney(address theOwner,uint ethers ) external onlyOwner returns(bool){
        if(ethers <= address(this).balance) 
            return theOwner.send(ethers);
        return false ; 
    }
    
}