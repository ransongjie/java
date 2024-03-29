package com.xcrj.sealed1;
//sealed 子类传递了密封性
public sealed class DogServiceImpl1 implements DogService permits MoreDogService {
}
