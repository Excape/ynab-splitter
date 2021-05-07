import React, {useEffect, useState} from "react";
import {AuditLog} from "../../types";
import {Grid, Loader} from 'semantic-ui-react';
import AuditLogCard from "./AuditLogCard";
import {getAllAuditlogs} from '../../services/auditlog-service';
import ReloadPageOnFail from '../ReloadPageOnFail';

const AuditLogList = () => {
    const [isLoaded, setIsLoaded] = useState(false)
    const [loadingFailed, setLoadingFailed] = useState(false)
    const [items, setItems] = useState([] as AuditLog[])

    useEffect(() => {
        getAllAuditlogs().then(result => {
                    setItems(sortAuditLogs(result))
                    setIsLoaded(true)
                },
                error => {
                    setLoadingFailed(true)
                }
            )
    }, []);

    function sortAuditLogs(auditLogs: AuditLog[]): AuditLog[] {
        let sortedItems = [...auditLogs]
        sortedItems.sort((a: AuditLog, b: AuditLog) =>
            new Date(b.date).getTime() - new Date(a.date).getTime())
        return sortedItems
    }

    if (loadingFailed) {
        return (<ReloadPageOnFail message={"Please reload to show audit logs"} />)
    }

    if (!isLoaded) {
        return (<Loader active inline='centered'/>)
    }

    return (
        <Grid stackable columns={1}>
            {items!
                .map(auditlog => (
                    <Grid.Column key={auditlog.id}>
                        <AuditLogCard auditlog={auditlog}/>
                    </Grid.Column>
                ))}
        </Grid>
    );
}

export default AuditLogList;