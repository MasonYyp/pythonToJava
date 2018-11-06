# coding=utf-8
import sys
import numpy as np

def say_information(name, age, sex, email):
    print(name)
    print(age)
    print(sex)
    print(email)
    return 0

def say_job():
    data=[1,2,3,4]
    data=np.array(data);
    print(data)
    job="I am IT" 
    print(job)
    return job


if __name__ == "__main__":
    # sys.argv获取从java获得参数
    say_information(sys.argv[0],sys.argv[1],sys.argv[2],sys.argv[3])
    say_job()
