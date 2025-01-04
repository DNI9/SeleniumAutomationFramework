#!/bin/bash

# Function to display number of processes found
count_processes() {
    local count=$(pgrep -c "chromedriver" 2>/dev/null || echo "0")
    echo "Found $count chromedriver process(es)"
}

# Display initial process count
echo "Checking for chromedriver processes..."
count_processes

# Kill all chromedriver processes
if pkill -9 "chromedriver" 2>/dev/null; then
    echo "Successfully terminated chromedriver processes"
else
    if [ $? -eq 1 ]; then
        echo "No chromedriver processes found"
        exit 0
    else
        echo "Error occurred while trying to kill chromedriver processes"
        exit 1
    fi
fi

# Optional: Kill associated Chrome browser instances
read -r -p "Do you want to kill associated Chrome browser instances too? (y/n) " response
if [[ "$response" =~ ^[Yy]$ ]]; then
    pkill -9 "chromium" 2>/dev/null
    echo "Chrome browser instances have been terminated"
fi