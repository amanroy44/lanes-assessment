import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';           // ← Added
import { LaneService } from '../../services/lane.service';
import { Router } from '@angular/router';
import { Lane } from '../../models/lane.model';

@Component({
  selector: 'app-lane-list',
  standalone: true,
  imports: [CommonModule, FormsModule],               // ← Added FormsModule
  templateUrl: './lane-list.component.html'
})
export class LaneListComponent implements OnInit {
  lanes: Lane[] = [];           // original full list
  filteredLanes: Lane[] = [];   // list shown in table
  loading = true;

  // Search
  searchId: number | null = null;

  constructor(
    private service: LaneService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadLanes();
  }

  loadLanes() {
    this.loading = true;
    this.service.getAllLanes().subscribe({
      next: (data) => {
        this.lanes = data;
        this.filteredLanes = [...data];   // show all initially
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
      }
    });
  }

  /** Search by ID */
  searchById() {
    if (!this.searchId) {
      this.filteredLanes = [...this.lanes];   // reset when search is empty
      return;
    }

    // Client-side filter (fast)
    this.filteredLanes = this.lanes.filter(lane => lane.id === this.searchId);

    // Optional: Also call backend for single lane (more accurate if list is stale)
    // this.service.getLaneById(this.searchId).subscribe({
    //   next: (lane) => this.filteredLanes = lane ? [lane] : [],
    //   error: () => this.filteredLanes = []
    // });
  }

  clearSearch() {
    this.searchId = null;
    this.filteredLanes = [...this.lanes];
  }

  goToCreate() {
    this.router.navigate(['/lanes/new']);
  }

  editLane(id: number) {
    this.router.navigate(['/lanes', id, 'edit']);
  }

  deleteLane(id: number) {
    if (confirm('Are you sure you want to delete this lane?')) {
      this.service.deleteLane(id).subscribe({
        next: () => {
          this.lanes = this.lanes.filter(l => l.id !== id);
          this.filteredLanes = this.filteredLanes.filter(l => l.id !== id);
          this.cdr.detectChanges();
        },
        error: (err) => console.error('Delete failed', err)
      });
    }
  }
}