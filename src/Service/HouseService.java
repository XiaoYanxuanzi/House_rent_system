package Service;

import domain.House;

/*
房屋出租系统的业务逻辑层
 */
public class HouseService {
    //保存House对象
    private House[] houses;
    //记录有多少个房屋信息
    private int houseNums = 1;
    //记录当前的id增长到哪个值
    private int idCounter = 1;

    //add方法 添加新对象 返回boolean
    public boolean add(House newHouse){
        if(houseNums == houses.length){//判断是否还能继续添加
            System.out.println("数组已满 不能再添加了");
            return false;
        }
        //房屋信息增长
        houses[houseNums++] = newHouse;
        //id自增长
        newHouse.setId(++idCounter);
        return true;
    }

    //del方法 删除一个房屋信息
    public boolean del(int delId){
        int index = -1;//用来记录和退出

        for(int i = 0;i<houseNums;i++){//根据房屋信息数量遍历数组(第一次做错了，以为是按照数组长度)
            if(delId == houses[i].getId()){//用参数寻找数组里面的id
                index = i;//记用index记录
            }
        }

        if(index == -1){//没找到想要的id，则退出
            return false;
        }

        for(int i = index;i<houseNums;i++){//根据房屋信息数量遍历数组(第一次做错了，以为是按照数组长度)
            houses[i] = houses[i+1];//找到id说明要成功删除，将后一位的数据转移到前一位来，避免为null后面的数据也无法保存
        }

//        --houseNums;  一开始我以为减掉一个房屋信息就可以了，看了人家的还是觉得自己考虑的不周全，如果只是减掉一个数字，没有让它为空，那么意义不大，
//        后面的数据还应该存在，正确的做法是用数组来减掉房屋信息，并且为空
        houses[--houseNums] = null;
        return true;
    }

    //find方法 用来查找房屋信息
    public House find(int findId){
        for (int i = 0;i<houseNums;i++){
            if(findId == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }

    public HouseService(int size) {//构造函数设置一个参数决定数组的大小
        //用来测试信息
        houses = new House[size];
        houses[0] = new House(1,"jack","111","湖南省",1600,"已出租");
    }

    //list方法 返回house
    public House[] list(){

        return houses;
    }
}
