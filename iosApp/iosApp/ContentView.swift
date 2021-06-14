import SwiftUI
import shared

struct ContentView: View {
    let vm = MainViewModel()
    var jsonText: Text
    
    init(){
        vm.loadTitleFromJson()
    }

	var body: some View {
        Text(vm.greeting)
        let jsonText = Text("")
        vm.jsonTitle.bind { [weak self](title) in
            guard let self = self else {return}
            jsonText.setText(title)
        }
        jsonText
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}
