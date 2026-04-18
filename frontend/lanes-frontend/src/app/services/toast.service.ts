import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  private container: HTMLDivElement | null = null;

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.createContainer();
    }
  }

  private createContainer() {
    this.container = document.createElement('div');
    this.container.style.position = 'fixed';
    this.container.style.top = '20px';
    this.container.style.right = '20px';
    this.container.style.zIndex = '9999';
    document.body.appendChild(this.container);
  }

  success(message: string, title = 'Success') {
    this.showToast(message, title, '#4caf50');
  }

  error(message: string, title = 'Error') {
    this.showToast(message, title, '#f44336');
  }

  warning(message: string, title = 'Warning') {
    this.showToast(message, title, '#ff9800');
  }

  private showToast(message: string, title: string, color: string) {
    if (!isPlatformBrowser(this.platformId) || !this.container) return;

    const toast = document.createElement('div');
    toast.style.backgroundColor = color;
    toast.style.color = 'white';
    toast.style.padding = '12px 20px';
    toast.style.marginBottom = '10px';
    toast.style.borderRadius = '6px';
    toast.style.boxShadow = '0 4px 12px rgba(0,0,0,0.3)';
    toast.style.minWidth = '280px';
    toast.style.opacity = '0';
    toast.style.transition = 'all 0.3s ease';

    toast.innerHTML = `
      <strong>${title}</strong><br>
      ${message}
    `;

    this.container.appendChild(toast);

    // Fade in
    setTimeout(() => toast.style.opacity = '1', 10);

    // Auto dismiss
    setTimeout(() => {
      toast.style.opacity = '0';
      setTimeout(() => toast.remove(), 350);
    }, 3500);
  }
}