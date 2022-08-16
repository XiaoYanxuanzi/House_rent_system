package View;

import Service.HouseService;
import Utils.Utility;
import domain.House;


/*
房屋出租系统展示层
有各种各样的界面
 */
public class HouseView {

    private boolean loop = true;//控制显示菜单
    private char key = ' ';//接收用户输入
    private char exit;//判断是否退出
    HouseService service = new HouseService(10);//创建对象

    //功能1 房屋信息添加
    //房间编号   姓名   电话   地址   月租   状态（未出租/已出租）
    public void addHouse(){
        System.out.println("===========添加房屋===========");
        System.out.println("姓名：");
        String name = Utility.readString(8);
        System.out.println("电话：");
        String phone = Utility.readString(12);
        System.out.println("地址：");
        String address = Utility.readString(20);
        System.out.println("月租：");
        int rent = Utility.readInt();
        System.out.println("状态：");
        String state = Utility.readString(3);
        //创建一个新的House对象
        House newHouse = new House(0,name,phone,address,rent,state);

        if(service.add(newHouse)){
            System.out.println("==========添加房屋成功==========");
        }else {
            System.out.println("==========添加房屋失败==========");
        }
    }

    //功能2 房屋信息修改
    public void updateHouse(){
        System.out.println("==========修改房屋信息==========");
        System.out.println("请选择待修改房屋编号(-1表示退出)");
        int updateId = Utility.readInt();
        if(updateId == -1){
            System.out.println("==========你放弃修改房屋信息==========");
            return;
        }

        //根据输入得到updateId,查找对象
        House house = service.find(updateId);
        if(service.find(updateId) == null){
            System.out.println("==========修改房屋信息编号不存在==========");
            return;
        }

        //这里的get方法就是用来显示当前信息的，方便修改
        System.out.println("姓名("+house.getName()+"):");
        String name = Utility.readString(8,"");
        if(!"".equals(name)){//通过set方法来进行修改，如果是空字符串，也就是用回车跳过的话，则不修改信息
            house.setName(name);
        }

        System.out.println("电话("+house.getPhone()+"): ");
        String phone = Utility.readString(12, "");
        if (!"".equals(phone)){
            house.setPhone(phone);
        }

        System.out.println("地址("+house.getAddress()+"): ");
        String address= Utility.readString(18, "");
        if (!"".equals(address)){
            house.setAddress(address);
        }

        System.out.println("租金("+house.getRent()+"): ");
        int sent = Utility.readInt(-1);
        if (sent!=-1){
            house.setRent(sent);
        }

        System.out.println("状态("+house.getState()+"): ");
        String state= Utility.readString(3, "");
        if (!"".equals(state)){
            house.setState(state);
        }

        System.out.println("修改房屋出租数据成功");
    }

    //功能3 房屋信息删除
    public void delHouse(){
        System.out.println("===========删除房屋信息===========");
        System.out.print("请输入待删除房屋的编号(-1退出):");
        int delId = Utility.readInt();//调用service层的参数
        if(delId == -1){
            System.out.println("==========放弃删除房屋信息==========");
            return;
        }


        char choice = Utility.readConfirmSelection();
        if (choice == 'Y') {//确定删除
            if(service.del(delId)){
                System.out.println("==========成功删除房屋信息==========");
            }else {
                System.out.println("==========该房屋信息不存在，删除失败===========");
            }
        } else {
            System.out.println("==========放弃删除房屋信息==========");
        }
    }

    //功能4 房屋信息查找
    public void findHouse(){
        System.out.println("==========查找房屋信息==========");
        System.out.println("请输入您要查找的房间编号(id)：");
        int findId = Utility.readInt();
        if(service.find(findId) == null){
            System.out.println("没有查到这个房间编号");
        }else {
            System.out.println(service.find(findId));
        }
    }

    //功能5 查找房屋列表
    public void listHouse(){
        System.out.println("==========房屋列表==========");
        System.out.println("编号\t\t\t姓名\t\t\t电话\t\t\t地址\t\t\t月租\t\t\t状态（未出租/已出租）");
        //得到所有房屋信息
        House[] houses = service.list();
        for(int i = 0;i<houses.length ;i++){
            if(houses[i] == null){//如果数组为空，则不显示
                break;
            }
            System.out.println(houses[i]);
        }

    }

    //功能6 退出系统
    public void exit(){
        exit = Utility.readConfirmSelection();

        if(exit == 'Y'){
            System.out.println("您已经成功退出系统");
            loop = false;
        }

    }

    //显示主菜单
    public void mainView(){

        do{
            System.out.println("==========欢迎来到房屋出租系统==========");
            System.out.println("\t\t 1.房 屋 信 息 添 加");
            System.out.println("\t\t 2.房 屋 信 息 修 改");
            System.out.println("\t\t 3.房 屋 信 息 删 除");
            System.out.println("\t\t 4.房 屋 信 息 查 找");
            System.out.println("\t\t 5.房 屋 列 表 查 看");
            System.out.println("\t\t 6.退 出 出 租 系 统");
            System.out.print("请输入您的选择(1-6):");
            key = Utility.readMenuSelection();

            switch (key){
                case '1':
                    addHouse();
                    break;
                case '2':
                    updateHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    findHouse();
                    break;
                case '5':
                    listHouse();
                    break;
                case '6':
                    exit();
                    break;
            }

        }while (loop);
    }
}
