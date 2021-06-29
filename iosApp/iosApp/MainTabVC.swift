//
//  MainTabVC.swift
//  MoviesIOS
//
//  Created by Anna Zharkova on 25.01.2021.
//

import UIKit
import shared

class MainTabVC: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .orange
        let presenter = MainViewModel()
        
        presenter.loadTitleFromJson()
        
        let label = UILabel()
        label.textColor = .white
        label.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(label)
        //label.text = presenter.greeting
        
        NSLayoutConstraint.activate([
            label.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            label.centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ])
        presenter.jsonTitle.bind { title in
            print(title)
            label.text = title as String?
        }
    }
}
