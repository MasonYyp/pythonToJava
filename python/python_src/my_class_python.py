# coding=utf-8

class Person:
    
    __name="张三";
    __age=0
    __sex='男'
    
    def set_name(self, name):
        self.__name = name
    def set_age(self, age):
        self.__age = age
    def set_sex(self, sex):
        self.__sex = sex
    
    def get_name(self):
        return self.__name
    def get_age(self):
        return self.__age
    def get_sex(self):
        return self.__sex
    
    # 初始化数据
    def __init__(self, name, age, sex):
        self.set_name(name)
        self.set_age(age)
        self.set_sex(sex)
    
    # 显示个人信息
    def sayMyInformation(self):
        print(self.get_name())
        print(self.get_age())
        print(self.get_sex())
        return 0
         
# 
# if __name__ =="__main__":
#     person = Person("王五", 2, "女")
#     person.sayMyInformation()
    