Map<Integer,Set<Integer>> map; 
int uid=1; 
PriorityQueue<Integer> leavedIds; 
public FileSharing(int m) {
    map=new TreeMap<>();
    leavedIds=new PriorityQueue<>();
}
public int join(List<Integer> ownedChunks) {
    int userID=uid; 
    if(leavedIds.size()>0) userID=leavedIds.poll();
    else uid++;
    map.put(userID, new HashSet<>(ownedChunks));
    return userID;
}
public void leave(int userID) {
    map.remove(userID);
    leavedIds.offer(userID);
}
public List<Integer> request(int userID, int chunkID) {
    List<Integer> res = new ArrayList<>();
    for(int user : map.keySet()){
        if(map.get(user).contains(chunkID)) res.add(user);
    }
    if(res.size()>0){
        Set<Integer> set=map.get(userID);
        set.add(chunkID);
        map.put(userID,set);
    }
    return res;
}
