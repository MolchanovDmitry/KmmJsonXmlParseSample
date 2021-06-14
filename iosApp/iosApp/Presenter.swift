//
//  Presenter.swift
//  iosApp
//
//  Created by Дмитрий on 14.06.2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class Presenter{

    let greet = Greeting()
    //let interactor:JsonInteractor
    
    init(){
        let repository = NetworkRepository()
        //interactor = JsonInteractor(repository: repository)
    }
    
    func getGreeting() -> String{
        return greet.greeting()
    }
}
