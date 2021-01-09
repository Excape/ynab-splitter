import {Button} from 'semantic-ui-react';
import React, {useState} from 'react';
import {UndoApprovalResult} from '../types';
import Cookies from 'js-cookie';

const UndoApproval = ({auditLogId}: {auditLogId: string}) => {
    const [result, setResult] = useState<UndoApprovalResult>()

    function onUndo() {
        const request = {
            auditLogId: auditLogId
        }

        fetch(`/api/v1/auditlog`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': Cookies.get("XSRF-TOKEN") ?? 'NULL'
            },
            body: JSON.stringify(request)
        })
            .then(result => result.json())
            .then(result => {
                setResult(result)
            })
    }

    return (
        <div>
            <Button onClick={() => onUndo()}>
                Undo
            </Button>
            {result !== undefined && (
                <div>
                    {result.success && (
                        <span>Transaction has been restored</span>
                    ) /* TODO: Add error message */ }
                </div>
            )}
        </div>
    )
}

export default UndoApproval