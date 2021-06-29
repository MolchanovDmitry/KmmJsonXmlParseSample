//
//  MainTabVC.swift
//  MoviesIOS
//
//  Created by Anna Zharkova on 25.01.2021.
//

import UIKit
import shared

class MainTabVC: UITabBarController {
    
    private let presenter = IosViewModel()
    
    lazy var setDBButton: UIButton = {
            let button = UIButton()
            button.setTitle("Сохранить", for: .normal)
            button.titleLabel?.textColor = .white
            button.titleLabel?.adjustsFontSizeToFitWidth = true
            button.translatesAutoresizingMaskIntoConstraints = false
        return button
        }()
        
        lazy var getDBButton: UIButton = {
            let button = UIButton()
            button.setTitle("Загрузить", for: .normal)
            button.titleLabel?.textColor = .white
            button.titleLabel?.adjustsFontSizeToFitWidth = true
            button.translatesAutoresizingMaskIntoConstraints = false
            return button
        }()
        
        lazy var dbTextField: UITextField = {
            let label = UITextField()
            label.translatesAutoresizingMaskIntoConstraints = false
            label.layer.borderWidth = 1.0
            label.layer.borderColor = UIColor.white.cgColor
            label.textColor = .white
            return label
        }()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .orange
        
        presenter.loadTitleFromJson()
        
        let jsonLabel = UILabel()
        jsonLabel.textColor = .white
        jsonLabel.translatesAutoresizingMaskIntoConstraints = false
    
        
        let greetingLabel = UILabel()
        greetingLabel.textColor = .white
        greetingLabel.translatesAutoresizingMaskIntoConstraints = false
        //greetingLabel.text = presenter.greeting
        
        view.addSubview(jsonLabel)
        view.addSubview(greetingLabel)

        view.addSubview(getDBButton)
        view.addSubview(setDBButton)
        view.addSubview(dbTextField)
        
        NSLayoutConstraint.activate([
            greetingLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            greetingLabel.topAnchor.constraint(equalTo: view.topAnchor, constant: 20),
            jsonLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            jsonLabel.topAnchor.constraint(equalTo: greetingLabel.bottomAnchor, constant: 10),
            dbTextField.topAnchor.constraint(equalTo: jsonLabel.topAnchor, constant: 20),
            dbTextField.heightAnchor.constraint(equalToConstant: 20),
            dbTextField.widthAnchor.constraint(equalToConstant: 50),
            dbTextField.leftAnchor.constraint(equalTo: view.leftAnchor, constant: 15),
            setDBButton.topAnchor.constraint(equalTo: jsonLabel.topAnchor, constant: 20),
            setDBButton.heightAnchor.constraint(equalToConstant: 20),
            setDBButton.widthAnchor.constraint(equalToConstant: 100),
            setDBButton.leftAnchor.constraint(equalTo: dbTextField.rightAnchor, constant: 20),
            getDBButton.topAnchor.constraint(equalTo: jsonLabel.topAnchor, constant: 20),
            getDBButton.heightAnchor.constraint(equalToConstant: 20),
            getDBButton.leftAnchor.constraint(equalTo: setDBButton.rightAnchor, constant: 20),
            getDBButton.rightAnchor.constraint(equalTo: view.rightAnchor, constant: -15)
        ])
        
        setDBButton.addTarget(self, action: #selector(setDBValue), for: .touchUpInside)
        getDBButton.addTarget(self, action: #selector(getDBValue), for: .touchUpInside)
        
        presenter.jsonTitle.bind { title in
            jsonLabel.text = title as String?
        }
        presenter.dbValue.bind{ dbPropValue in
            self.dbTextField.text = dbPropValue as String?
        }
    }
    
    @objc private func setDBValue() {
        presenter.setDbValue(newValue: dbTextField.text ?? "empty")
    }
    
    @objc private func getDBValue() {
        presenter.getDbValue()
    }
}
        
        
