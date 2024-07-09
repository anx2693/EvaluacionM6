const depositAmountInput = document.getElementById('depositAmount');
const formattedAmountSpan = document.getElementById('formattedAmount');

depositAmountInput.addEventListener('input', function() {
  const amount = parseFloat(this.value) || 0;
  const formattedCLP = amount.toLocaleString('es-CL', { style: 'currency', currency: 'CLP' });
  formattedAmountSpan.textContent = formattedCLP;
});
