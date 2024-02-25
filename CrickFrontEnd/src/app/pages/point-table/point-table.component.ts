import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../../services/api-call.service';
import { error } from 'console';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-point-table',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './point-table.component.html',
  styleUrl: './point-table.component.scss'
})
export class PointTableComponent implements OnInit{
  pointtable: any;
  tableHeading :any
 constructor(private _api:ApiCallService){

 }
  ngOnInit(): void {

    this._api.getPointTable().subscribe({
        next:data=>{
          this.pointtable = data
          // console.log(this.pointtable)
          this.tableHeading=[...this.pointtable[0]]
          // console.log(this.tableHeading)
        },
        error:error=>{
          console.log(error)
        }
})  }
}
