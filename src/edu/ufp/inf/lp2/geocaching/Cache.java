package edu.ufp.inf.lp2.geocaching;

import edu.princeton.cs.algs4.ST;

import java.util.ArrayList;

public class Cache{ // é necessario fazer extends do userBasic (?)


  public ST<String, UserBasic> hUsers= new ST<>();
  public ArrayList<CacheLogs> cacheLogs= new ArrayList<>();
  public ArrayList<Objeto> arrayListObjeto=new ArrayList<>();

  public UserBasic userCreator;
  public CacheDiff type;
  public String serialNumber;



  public int x;
  public int y;
  public String regiao;

  public String getRegiao() {
    return regiao;
  }

  public void setRegiao(String regiao) {
    this.regiao = regiao;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }


  public Cache(String serialNumber, CacheDiff type , UserBasic userCreator, ArrayList arrayListObjeto , int x, int y, String regiao) {
    this.serialNumber = serialNumber;
    this.type=type;
    this.userCreator = userCreator;
    this.arrayListObjeto=arrayListObjeto;
    this.x = x;
    this.y = y;
    this.regiao=regiao;
  }


  public void addObjectToArrayList(Objeto objeto) throws AlreadyRegisteredObjectException {
    if (!arrayListObjeto.contains(objeto)) {
      arrayListObjeto.add(objeto);
    }
    throw new AlreadyRegisteredObjectException("Object already registered!");
  }

  public boolean removeObjectFromCache(String nameItem) {
    for (int i = 0; i < arrayListObjeto.size(); i++) {
      arrayListObjeto.get(i).nameItem.equals(nameItem);
      if (arrayListObjeto.get(i).nameItem.equals(nameItem)) {
        return arrayListObjeto.remove(arrayListObjeto.get(i));
      }
    }
    return false;

  }

  public void printObjetoFromCache(){ //printa todos os objetos da cache
    System.out.println("Item da Lista de objetos: ");
    for (int i = 0; i < arrayListObjeto.size(); i++){
      System.out.println(arrayListObjeto.get(i).nameItem);
    }
  }

  public void addVisitante(UserBasic u1){
    hUsers.put(""+System.currentTimeMillis(),u1);
  }

  public void printhUsers(){
    for (String user : hUsers.keys()){
      System.out.println(hUsers.get(user));
    }
    System.out.println("\n");
  }

  @Override
  public String toString() {
    return "Cache{" +
            "userCreator=" + userCreator.name +
            ", type=" + type +
            ", serialNumber='" + serialNumber + '\'' +
           //precisar do for
            ", x=" + x +
            ", y=" + y +
            ", regiao='" + regiao + '\'' +
            '}';
  }
}

