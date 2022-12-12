import SwiftUI

struct HideableSearchTextField<Destination: View>: View {
    
    var onSearchToggled: () -> Void
    var destinationProvider: () -> Destination
    var isSearchActive: Bool
    @Binding var searchText: String
    
    var body: some View {
        //Row is HStack
        HStack {
            TextField("Search...", text: $searchText)
                .textFieldStyle(.roundedBorder)
                .opacity(isSearchActive ? 1 : 0)
            if !isSearchActive {
                //Spacer in Swift UI means occupy all the space you get
                Spacer()
            }
            Button(action: onSearchToggled) {
                //if the search active show xmark then show magnifyingglass
                Image(systemName: isSearchActive ? "xmark" : "magnifyingglass")
                    .foregroundColor(.black)
            }
            NavigationLink(destination: destinationProvider()) {
                //the plus icon
                Image(systemName: "plus")
                    .foregroundColor(.black)
            }
        }
    }
}

struct HideableSearchTextField_Previews: PreviewProvider {
    static var previews: some View {
        HideableSearchTextField(
            onSearchToggled: {},
            destinationProvider: { EmptyView() },
            isSearchActive: true,
            searchText: .constant("YouTube")
        )
    }
}
