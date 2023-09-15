import {Component, EventEmitter, Input, Output, ViewChild} from '@angular/core';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.scss']
})
export class SearchBarComponent {
  @ViewChild('searchInput') searchInput;
  @Input() query = '';
  @Output() search: EventEmitter<string> = new EventEmitter<string>();

  onSearch(query: string): void {
    this.search.emit(query);
  }

}
