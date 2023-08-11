document.addEventListener('DOMContentLoaded', function() {
    const createBtn = document.getElementById('createBtn');
    createBtn.addEventListener('click', createReimbursementForm);
});
function createReimbursementForm() {
    const formContainer = document.getElementById('form-container');

    formContainer.innerHTML = '';

    const form = document.createElement('form');
    form.id = 'reimbursement-form';

    const dateRangeLabel = document.createElement('label');
    dateRangeLabel.textContent = 'Select Date Range:';
    const dateRangeStartInput = document.createElement('input');
    dateRangeStartInput.type = 'date';
    dateRangeStartInput.name = 'dateRangeStart';
    dateRangeStartInput.required = true;

    const dateRangeEndInput = document.createElement('input');
    dateRangeEndInput.type = 'date';
    dateRangeEndInput.name = 'dateRangeEnd';
    dateRangeEndInput.required = true;

    const carUsageCheckbox = document.createElement('input');
    carUsageCheckbox.type = 'checkbox';
    carUsageCheckbox.name = 'carUsage';
    carUsageCheckbox.id = 'carUsage';
    const carUsageLabel = document.createElement('label');
    carUsageLabel.textContent = 'Car usage';
    carUsageLabel.setAttribute('for', 'carUsage');

    const carDistanceLabel = document.createElement('label');
    carDistanceLabel.textContent = 'Claim for Car distance:';
    const distanceInput = document.createElement('input');
    distanceInput.type = 'number';
    distanceInput.name = 'distance';
    distanceInput.placeholder = 'Driven distance';
    distanceInput.disabled = true;

    carUsageCheckbox.addEventListener('change', function () {
        distanceInput.disabled = !this.checked;
    });

    const selectedDaysContainer = document.createElement('div');
    selectedDaysContainer.id = 'selected-days-container';

    const receiptsLabel = document.createElement('label');
    receiptsLabel.textContent = 'Receipts:';
    const receiptsSelect = document.createElement('select');
    receiptsSelect.name = 'receipts';
    const receiptOptions = ['Taxi', 'Hotel', 'Plane Ticket', 'Train'];
    for (const optionText of receiptOptions) {
        const option = document.createElement('option');
        option.value = optionText;
        option.textContent = optionText;
        receiptsSelect.appendChild(option);
    }

    const addReceiptButton = document.createElement('button');
    addReceiptButton.type = 'button';
    addReceiptButton.textContent = 'Add New Receipt';
    addReceiptButton.addEventListener('click', addNewReceipt);

    const receiptContainer = document.createElement('div');
    receiptContainer.id = 'receipt-container';

    const submitButton = document.createElement('button');
    submitButton.type = 'button';
    submitButton.textContent = 'Calculate Reimbursement';
    submitButton.onclick = calculateReimbursement;

    const totalReimbursementLabel = document.createElement('p');
    totalReimbursementLabel.id = 'total-reimbursement';
    totalReimbursementLabel.textContent = 'Total Reimbursement: $0.00';

    const selectedDaysContainerWrapper = document.createElement('div');
    selectedDaysContainerWrapper.appendChild(selectedDaysContainer);

    form.appendChild(dateRangeLabel);
    form.appendChild(dateRangeStartInput);
    form.appendChild(dateRangeEndInput);
    form.appendChild(document.createElement('br'));
    form.appendChild(selectedDaysContainerWrapper);
    form.appendChild(document.createElement('br'));
    form.appendChild(carUsageLabel);
    form.appendChild(carUsageCheckbox);
    form.appendChild(document.createElement('br'));
    form.appendChild(carDistanceLabel);
    form.appendChild(distanceInput);
    form.appendChild(document.createElement('br'));
    form.appendChild(receiptsLabel);
    form.appendChild(receiptsSelect);
    form.appendChild(addReceiptButton);
    form.appendChild(receiptContainer);
    form.appendChild(document.createElement('br'));
    form.appendChild(submitButton);
    form.appendChild(document.createElement('br'));
    form.appendChild(totalReimbursementLabel);

    formContainer.appendChild(form);

    function addNewReceipt() {
        const selectedReceipt = receiptsSelect.value;
        const receiptAmount = prompt(`Enter the amount for ${selectedReceipt}:`);
        if (receiptAmount !== null && receiptAmount.trim() !== '') {
            const receiptEntry = document.createElement('div');
            receiptEntry.textContent = `${selectedReceipt}: $${parseFloat(receiptAmount).toFixed(2)}`;
            receiptContainer.appendChild(receiptEntry);
        }
    }
    function updateSelectedDays() {
        selectedDaysContainerWrapper.innerHTML = '';
        const startDate = new Date(dateRangeStartInput.value);
        const endDate = new Date(dateRangeEndInput.value);
        let currentDate = new Date(startDate);

        while (currentDate <= endDate) {
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.name = 'selectedDays';
            checkbox.value = currentDate.toISOString().split('T')[0];
            const label = document.createElement('label');
            label.textContent = currentDate.toDateString();
            selectedDaysContainerWrapper.appendChild(checkbox);
            selectedDaysContainerWrapper.appendChild(label);

            currentDate.setDate(currentDate.getDate() + 1);
        }
    }

    dateRangeStartInput.addEventListener('change', updateSelectedDays);
    dateRangeEndInput.addEventListener('change', updateSelectedDays);
    updateSelectedDays();
}

function calculateReimbursement() {
    const totalReimbursementField = document.getElementById('total-reimbursement');
    totalReimbursementField.textContent = 'Total Reimbursement: $123.45';
}