package com.xcrj.sealed1;
//
public sealed interface DogService extends AnimalService permits DogServiceImpl1, DogServiceImpl2, DogServiceImpl3 {

}
