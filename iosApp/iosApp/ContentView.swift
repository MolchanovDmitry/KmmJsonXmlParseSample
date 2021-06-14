import SwiftUI
import shared

struct ContentView: View {
    let presenter = Presenter()

	var body: some View {
        Text(presenter.getGreeting())
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}
