import UIKit
import shared

class MainVC: UIViewController {
    lazy var setDBButton: UIButton = {
        return makeButton(text: "Сохранить")
    }()
    
    lazy var getDBButton: UIButton = {
        return makeButton(text: "Загрузить")
    }()
    
    private let presenter = IosViewModel()
    
    lazy var dbTextField: UITextField = {
        let field = makeTextField()
        field.backgroundColor = .white
        field.textColor = .black
        return field
    }()
    
    lazy var greetingLabel: UILabel = {
       return makeLabel()
    }()
    
    lazy var jsonLabel: UILabel = {
       return makeLabel()
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .orange
        greetingLabel.text = presenter.greeting
        presenter.loadTitleFromJson()
        setupLayout()
        setDBButton.addTarget(self, action: #selector(setDBValue), for: .touchUpInside)
        getDBButton.addTarget(self, action: #selector(getDBValue), for: .touchUpInside)
        
        presenter.jsonTitle.bind { [weak self] title in
            self?.jsonLabel.text = title as String?
        }
        presenter.dbValue.bind{ [weak self] dbPropValue in
            self?.dbTextField.text = dbPropValue as String?
        }
    }
    
    func setupLayout() {
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
    }
    
    func makeButton(text: String) -> UIButton {
        let button = UIButton()
        button.setTitle(text, for: .normal)
        button.titleLabel?.textColor = .white
        button.titleLabel?.adjustsFontSizeToFitWidth = true
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }
    
    func makeLabel() -> UILabel {
        let label = UILabel()
        label.textColor = .white
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }
    
    func makeTextField() -> UITextField {
        let field = UITextField()
        field.translatesAutoresizingMaskIntoConstraints = false
        field.textColor = .white
        return field
    }
    
    @objc private func setDBValue() {
        presenter.setDbValue(newValue: dbTextField.text ?? "н\\д")
    }
    
    @objc private func getDBValue() {
        presenter.getDbValue()
    }
}


