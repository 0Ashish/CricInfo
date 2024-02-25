import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-match-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './match-card.component.html',
  styleUrl: './match-card.component.scss'
})
export class MatchCardComponent {
    @Input() match:any
}
