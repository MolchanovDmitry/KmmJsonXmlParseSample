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
        
        let jsonLabel = UILabel()
        jsonLabel.textColor = .white
        jsonLabel.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(jsonLabel)
        
        let greetingLabel = UILabel()
        greetingLabel.textColor = .white
        greetingLabel.adjustsFontForContentSizeCategory = false
        greetingLabel.text = presenter.greeting
        view.addSubview(greetingLabel)
        
        NSLayoutConstraint.activate([
            greetingLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            greetingLabel.centerYAnchor.constraint(equalTo: view.centerYAnchor)
            //jsonLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            //jsonLabel.topAnchor.constraint(equalTo: greetingLabel.bottomAnchor, constant: 10)
        ])
        presenter.jsonTitle.bind { title in
            jsonLabel.text = title as String?
        }
    }
}
