import Foundation
import shared
//extension in ios means that it's an View of the class
//ObservableObject in Ios is ViewModel in Android
//@MainActor in Ios means Main thread
extension NoteListScreen {
    @MainActor class NoteListViewModel: ObservableObject {
        //constructure
        private var noteDataSource: NoteDataSource? = nil
        
        private let searchNotes = SearchNotes()
        
        //@Published state , Observable
        private var notes = [Note]()
        @Published private(set) var filteredNotes = [Note]()
        @Published var searchText = "" {
            didSet {
                self.filteredNotes = searchNotes.execute(notes: self.notes, query: searchText)
            }
        }
        @Published private(set) var isSearchActive = false
        
        init(noteDataSource: NoteDataSource? = nil) {
            self.noteDataSource = noteDataSource
        }
        
        func loadNotes() {
            noteDataSource?.getAllNotes(completionHandler: { notes, error in
                self.notes = notes ?? []
                self.filteredNotes = self.notes
            })
        }
        //Int46 == Long
        func deleteNoteById(id: Int64?) {
            if id != nil {
                noteDataSource?.deleteNoteById(id: id!, completionHandler: { error in
                    self.loadNotes()
                })
            }
        }
        
        func toggleIsSearchActive() {
            isSearchActive = !isSearchActive
            if !isSearchActive {
                searchText = ""
            }
        }
        
        func setNoteDataSource(noteDataSource: NoteDataSource) {
            self.noteDataSource = noteDataSource
        }
    }
}
