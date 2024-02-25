import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../../services/api-call.service';
import { error } from 'console';
import { CommonModule } from '@angular/common';
import { MatchCardComponent } from '../../../components/match-card/match-card.component';


@Component({
  selector: 'app-live',
  standalone: true,
  imports: [CommonModule,MatchCardComponent],
  templateUrl: './live.component.html',
  styleUrl: './live.component.scss'
})
export class LiveComponent implements OnInit {

    livematches :any
        constructor(private _api:ApiCallService){
             
        }
        ngOnInit(): void{
          this.loadliveMatches();
        }
  private loadliveMatches() {
    this._api.getLiveMatches().subscribe({
      next:data=>{
        console.log(data)
        this.livematches = data 
      },
      error:error=>{
        console.log(error)
      }
    })
  }
}
