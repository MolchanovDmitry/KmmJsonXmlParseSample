import SwiftUI
import shared

struct ContentView: View {
    let greeting = Greeting().greeting()

	var body: some View {
        Text(greeting)
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
	ContentView()
	}
}
