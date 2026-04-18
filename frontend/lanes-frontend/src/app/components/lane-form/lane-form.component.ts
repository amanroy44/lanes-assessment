import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LaneService } from '../../services/lane.service';
import { Lane } from '../../models/lane.model';
import { ToastService } from '../../services/toast.service';

@Component({
  selector: 'app-lane-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './lane-form.component.html'
})
export class LaneFormComponent implements OnInit {

  lane: Lane = { name: '', originCity: '', destinationCity: '' };
  isEdit = false;
  id!: number;
  loading = false;
  errorMessage = '';

  constructor(
    private service: LaneService,
    private route: ActivatedRoute,
    private router: Router,
    private toast: ToastService
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.id = idParam ? Number(idParam) : 0;

    if (this.id) {
      this.isEdit = true;
      this.loadLane();
    }
  }

  loadLane() {
    this.service.getLaneById(this.id).subscribe({
      next: (data) => this.lane = data,
      error: () => this.toast.error('Failed to load lanes'),
    });
  }

  onSubmit() {
    if (!this.lane.name?.trim()) {
      this.toast.warning('Name is required!');
      return;
    }

    this.loading = true;
    this.errorMessage = '';

    const request = this.isEdit 
      ? this.service.updateLane(this.id, this.lane)
      : this.service.createLane(this.lane);

    request.subscribe({
      next: () => {
        this.toast.success(
          this.isEdit ? 'Lane updated successfully!' : 'Lane created successfully!',
          'Success'
        );
        this.router.navigate(['/lanes']);
      },
      error: (err) => {
        this.toast.error(err.error?.message || 'Operation failed', 'Error');
        this.loading = false;
      }
    });
  }

  cancel() {
    this.router.navigate(['/lanes']);
  }
}